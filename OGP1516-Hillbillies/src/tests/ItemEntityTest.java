package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import hillbillies.model.Boulder;
import hillbillies.model.ItemEntity;
import hillbillies.model.World;
import hillbillies.part2.facade.Facade;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.world.Position;
import ogp.framework.util.ModelException;

public class ItemEntityTest {

	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	private World world;
	private Facade facade;
	private ItemEntity boulder;
	
	@Before
	public void setUp() throws ModelException {
		int[][][] types = new int[5][5][5];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_TREE;
		types[1][1][2] = TYPE_WORKSHOP;
		this.facade = new Facade();
		world = facade.createWorld(types, new DefaultTerrainChangeListener());
		boulder = new Boulder(world, new Position(0,0,0));
		world.addEntity(boulder);
		}
	
	@Test
	public void constructor_LegalCase() {
		ItemEntity boulder = new Boulder(world, new Position(0,0,0));
		assertEquals(world, boulder.getWorld());
		assertEquals(new Position(0.5,0.5,0.5), boulder.getPosition());
		assertFalse(boulder.getWeight() > Boulder.MAX_WEIGHT);
		assertFalse(boulder.getWeight() < Boulder.MIN_WEIGHT);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructor_IllegalCase() {
		new Boulder(world, new Position(-1,0,0));
	}
	
	@Test
	public void canHaveAsWeight_TrueCase() {
		assertTrue(ItemEntity.canHaveAsWeight(25));
	}
	
	@Test
	public void canHaveAsWeight_WeightTooSmall() {
		assertFalse(ItemEntity.canHaveAsWeight(5));
	}
	
	@Test
	public void canHaveAsWeight_WeightTooHigh() {
		assertFalse(ItemEntity.canHaveAsWeight(55));
	}
	
	@Test
	public void canStandOn_TrueCase() {
		assertTrue(boulder.canStandOn(new Position(1,1,2)));
	}
	
	@Test
	public void canStandOn_FalseCase() {
		assertFalse(boulder.canStandOn(new Position(1,1,3)));
	}
}