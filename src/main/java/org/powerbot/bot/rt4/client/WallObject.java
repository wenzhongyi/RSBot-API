package org.powerbot.bot.rt4.client;

import org.powerbot.bot.*;

public class WallObject extends BasicObject {
	private static final Reflector.FieldCache a = new Reflector.FieldCache(),
			b = new Reflector.FieldCache();

	public WallObject(final Reflector engine, final Object parent) {
		super(engine, parent);
	}

	@Override
	public long getUid() {
		return reflector.accessLong(this, a);
	}

	@Override
	public int getMeta() {
		return reflector.accessInt(this, b);
	}


	@Override
	public int getX() {
		return -1;
	}

	@Override
	public int getZ() {
		return -1;
	}

	@Override
	public int getX1() {
		return -1;
	}

	@Override
	public int getY1() {
		return -1;
	}

	@Override
	public int getX2() {
		return -1;
	}

	@Override
	public int getY2() {
		return -1;
	}
}
