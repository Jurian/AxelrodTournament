package uu.mas.gui;

import jade.core.AID;

public class MovingAvgChartPanel extends ChartPanel {

	public void update(AID player, int score) {
		if(!map.containsKey(player)) {
			PlayerTrace pt = new PlayerTrace(player);
			map.put(player, pt);
			chart.addTrace(pt.trace);
			pt.insert(score);
		} else {
			double avg = map.get(player).trace.getMaxY();
			int n = map.get(player).trace.getSize();
			
			avg -= avg / n;
			avg += score / n;
			
			map.get(player).insert(avg);
		}
	}
}
