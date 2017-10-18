package uu.mas.player;

import uu.mas.strategy.AlwaysDefectStrategy;

@SuppressWarnings("serial")
public class AlwaysDefectPlayer extends Player {

	@Override
	protected void setup() {
		super.setup();
		addBehaviour( new AlwaysDefectStrategy() );
	}

	
}
