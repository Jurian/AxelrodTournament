package uu.mas.gui;

import jade.core.AID;

public class CumulativeChartPanel extends ChartPanel {
	
	public void update(AID player, int score) {
		if(!map.containsKey(player)) {
			PlayerTrace pt = new PlayerTrace(player);
			map.put(player, pt);
			chart.addTrace(pt.trace);
			pt.insert(score);
		} else {
			int maxY = (int) map.get(player).trace.getMaxY();
			map.get(player).insert(maxY + score);
		}
	}
}
