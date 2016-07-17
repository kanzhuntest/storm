package com.usc.helios;

import backtype.storm.Config;
import backtype.storm.StormSubmitter;
import backtype.storm.generated.AlreadyAliveException;
import backtype.storm.generated.InvalidTopologyException;
import backtype.storm.generated.StormTopology;
import backtype.storm.topology.TopologyBuilder;

public class TopoMain {
	public static void main(String[] args){
		TopologyBuilder topologyBuilder = new TopologyBuilder();
		
		topologyBuilder.setSpout("randomspout", new RandomSpout(),4);
		
		topologyBuilder.setBolt("upperbolt", new UpperBolt(), 4).shuffleGrouping("randomspout");
		
		topologyBuilder.setBolt("suffixbolt", new SuffixBolt(),4).shuffleGrouping("upperbolt");
		
		StormTopology topo = topologyBuilder.createTopology();
		
		Config conf = new Config();
		conf.setNumWorkers(4);
		conf.setDebug(true);
		conf.setNumAckers(0);
		
		//提交
		try {
			StormSubmitter.submitTopology("demotopo", conf, topo);
		} catch (AlreadyAliveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidTopologyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
