package org.powerbot.bot.rt4.client;

import org.powerbot.bot.ReflectProxy;
import org.powerbot.bot.Reflector;

public abstract class BasicObject extends ReflectProxy {

	public BasicObject(final Reflector engine, final Object parent) {
		super(engine, parent);
	}

	public abstract long getUid();

	public abstract int getMeta();

	public abstract int getX();

	public abstract int getZ();

	public abstract int getX1();

	public abstract int getY1();

	public abstract int getX2();

	public abstract int getY2();
}
