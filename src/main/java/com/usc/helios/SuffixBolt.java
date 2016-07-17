package com.usc.helios;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

public class SuffixBolt extends BaseBasicBolt{
	FileWriter fileWriter = null;
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		try {
			fileWriter = new FileWriter("/home/hadoop/"+UUID.randomUUID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String upper_word = tuple.getString(0);
		
		String result = upper_word+"_suffix";
		
		try {
			
			fileWriter.append(result);
			fileWriter.append("\n");
			fileWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
	}

}
