package com.springboot.chapter7.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redis")

public class RedisController {
	@Autowired
	private RedisTemplate<String, String> redisTemplate = null;
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate = null;
	
	@RequestMapping ("/stringAndHash")
	@ResponseBody
	public Map<String,Object> testStringAndHash(){
		redisTemplate.opsForValue().set("key1", "Value1");
		redisTemplate.opsForValue().set("int_key", "1");
		stringRedisTemplate.opsForValue().set("int", "1");
		System.out.println(redisTemplate.opsForValue().get("key1"));
		//运算
		stringRedisTemplate.opsForValue().increment("int");
		stringRedisTemplate.opsForValue().decrement("int");
		Map<String,Object> hash = new HashMap<String,Object>();
		hash.put("fields1","value1");
		hash.put("fiedls2", "value2");
		//存入一个散列数据类型
		stringRedisTemplate.opsForHash().putAll("hash1", hash);
		//新增一个hash字段
		stringRedisTemplate.opsForHash().put("hash1","field3","value3");
		System.out.println(stringRedisTemplate.opsForHash().get("hash1", "field3"));
		//绑定散列操作的key，这样可以连续对一个散列数据类型操作
		BoundHashOperations<String, String, String> hashOps = stringRedisTemplate.boundHashOps("hash1");
		//可以直接连续删掉字段
		hashOps.delete("fields1","fields2");
		//
		hashOps.put("fields4", "value5");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", hashOps.get("fields4"));
		return map;
	}
	
	//list
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> testList(){
		//插入两个列表，查看列表顺序
		stringRedisTemplate.opsForList().leftPushAll("list1", "v2","v4","v6","v8","v10");
		stringRedisTemplate.opsForList().rightPushAll("list2", "v1","v3","v5","v7","v9");
		//list2赋值给listops
		BoundListOperations<String, String> listOps = stringRedisTemplate.boundListOps("list2");
		//从右弹出
		Object result1 = listOps.rightPop();
		System.out.println("result1="+result1);
		Object result2 = listOps.index(1);
		System.out.println("result1"+result2);
		//从左边插入链表
		listOps.leftPush("v0");
		//查看链表长度
		Long size  = listOps.size();
		System.out.println("list size()="+size);
		//查看链表成员
		List<String> elements = listOps.range(0, size -2);
		System.out.println(elements);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}
	
	//快速排序，积分
	@RequestMapping("/zset")
	@ResponseBody
	public Map<String,Object> testZset(){
		Set<TypedTuple<String>> typedTupleSet = new HashSet<>();
		for(int i = 1; i<9; i++) {
			double score = i*0.1;
			TypedTuple<String> typedTuple = new DefaultTypedTuple<String>("Vaule"+i,score);
			typedTupleSet.add(typedTuple);
		}
		
		stringRedisTemplate.opsForZSet().add("zset1", typedTupleSet);
		
		//list2赋值给listops
		BoundZSetOperations<String, String> zsetOps = stringRedisTemplate.boundZSetOps("zset1");
		//从右弹出
		zsetOps.add("value10",0.26);
		Set<String> setRange = zsetOps.range(1, 6);
		//按积分排序
		Set<String> setScore = zsetOps.rangeByScore(0.2, 0.6);
		System.out.println("zsetOps1="+zsetOps);
		Range range = new Range();
		range.gt("Value3"); //大于value3
		//range.gte("Value8");//大于等于value3
		//range.lt("Value8");//小于value3
		range.lte("Value8");//小于等于value3
		Set<String> setLex = zsetOps.rangeByLex(range);
		System.out.println("setLex="+setLex);
		//删除元素
		zsetOps.remove("Value9","Value2");
		//展示分数
		Double score = zsetOps.score("Value8");
		//在下标区间下，按分数排序，同时返回value和score
		Set<TypedTuple<String>> rangeSet = zsetOps.rangeByScoreWithScores(1, 6);
		//在分数区间下，按分数排序，同时返回score,value
		Set<TypedTuple<String>> scoreSet = zsetOps.rangeByScoreWithScores(1, 6);
		System.out.println("scoreSet="+scoreSet+";rangeSet="+rangeSet);
		//按从大到小的排序
		Set<String> reverseSet = zsetOps.reverseRange(2, 9);
		System.out.println("reverseSet="+reverseSet);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}
	
	//使用redis管道流水线提高性能
	@RequestMapping("/pipeline")
	@ResponseBody
	public Map<String,Object> testPipeline(){
	    Long start = System.currentTimeMillis();
	   /*
	    List list = (List) redisTemplate.executePipelined((RedisOperations operations)->
	    {
	    	for(int i = 1;i<10000; i++) {
	    		operations.opsForValue().set("Pipeline_"+i, "value_"+i);
	    		String value = (String) operations.opsForValue().get("Pipeline_"+i);
	    		if(i == 100000) {
	    			System.out.println("命令指示进入队列，所以值为空{"+value +"}");
	    		}
	    	}
	    	return null;
	    });
	    */
	  Long end =System.currentTimeMillis();
	  System.out.println("耗时"+(end - start)+"毫秒");
	    Map<String,Object> map = new HashMap<String,Object>();
		map.put("success", true);
		return map;
	}

}
