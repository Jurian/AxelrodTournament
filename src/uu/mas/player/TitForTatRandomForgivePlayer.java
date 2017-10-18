package uu.mas.player;

import uu.mas.strategy.TitForTatRandomForgiveStrategy;


@SuppressWarnings("serial")
public class TitForTatRandomForgivePlayer extends Player {

	@Override
	protected void setup() {
		super.setup();
		addBehaviour( new TitForTatRandomForgiveStrategy() );
	}

	
}
