package com.usc.helios;

import java.util.Map;
import java.util.Random;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import backtype.storm.utils.Utils;

public class RandomSpout extends BaseRichSpout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	SpoutOutputCollector collector = null;
	
	String[] goods = {"iphone","xiaomi","meizu","zhongxing","huawei","moto","sumsung","simens"};
	
	public void nextTuple() {
		Random random = new Random();
		String good = goods[random.nextInt(goods.length)];
		
		collector.emit(new Values(good));
		
		Utils.sleep(500);
	}

	/**
	 * 只在开始的时候调用一次
	 */
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * 定义tuple的schem
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("src_word"));
	}
	
}
