package org.powerbot.script.rt4;

import java.awt.Point;
import java.awt.Rectangle;

import org.powerbot.script.Identifiable;
import org.powerbot.script.Nameable;
import org.powerbot.script.Random;
import org.powerbot.script.Stackable;

public class Item extends Interactive implements Identifiable, Nameable, Stackable {
	private static final int BASE_X = 560, BASE_Y = 210;
	private static final int WIDTH = 42, HEIGHT = 36;
	final Component component;
	private final int inventory_index, id;
	private final int stack;

	public Item(final ClientContext ctx, final Component component, final int id, final int stack) {
		this(ctx, component, -1, id, stack);
	}

	public Item(final ClientContext ctx, final Component component, final int inventory_index, final int id, final int stack) {
		super(ctx);
		this.component = component;
		this.inventory_index = inventory_index;
		this.id = id;
		this.stack = stack;
	}

	@Override
	public int id() {
		return id;
	}

	@Override
	public Point centerPoint() {
		if (inventory_index != -1) {
			final int x = BASE_X + (inventory_index % 4) * WIDTH, y = BASE_Y + (inventory_index / 4) * HEIGHT;
			return new Point(x + WIDTH / 2, y + HEIGHT / 2);
		}
		return component.centerPoint();
	}

	@Override
	public String name() {
		return "";
	}

	@Override
	public int stackSize() {
		return stack;
	}

	@Override
	public Point nextPoint() {
		if (inventory_index != -1) {
			final Rectangle r = new Rectangle(BASE_X + (inventory_index % 4) * WIDTH, BASE_Y + (inventory_index / 4) * HEIGHT, WIDTH, HEIGHT);
			return new Point(Random.nextInt(r.x, r.x + r.width), Random.nextInt(r.y, r.y + r.height));
		}
		return component.nextPoint();
	}

	@Override
	public boolean contains(final Point point) {
		if (inventory_index != -1) {
			final Rectangle r = new Rectangle(BASE_X + (inventory_index % 4) * WIDTH, BASE_Y + (inventory_index / 4) * HEIGHT, WIDTH, HEIGHT);
			return r.contains(point);
		}
		return component.contains(point);
	}

	@Override
	public boolean valid() {
		return component != null && component.visible() && id != -1;
	}
}
