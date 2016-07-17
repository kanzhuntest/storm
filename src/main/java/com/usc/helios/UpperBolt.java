package com.usc.helios;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class UpperBolt extends BaseBasicBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void execute(Tuple tuple, BasicOutputCollector collector) {
		
		//tuple.getValueByField("");
		
		//tuple.getStringByField("");
		
		
		String src_word = tuple.getString(0);
				
		src_word = src_word.toUpperCase();
		
		collector.emit(new Values(src_word));
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("upper_word"))
;	}

}
