package org.powerbot.script;

import org.powerbot.script.rt4.CacheModelConfig;
import org.powerbot.script.rt4.Model;

import java.awt.*;

/**
 * Modelable
 * An entity which contains a model.
 */
public interface Modelable {

	/**
	 * The local X position of the entity
	 * @return local x
	 */
	int localX();

	/**
	 * The local Y position of the entity
	 * @return local y
	 */
	int localY();

	/**
	 * The orientation of the entity (which way it's facing)
	 * @return model orientation
	 */
	int modelOrientation();

	/**
	 * Model ids to load from the cache
	 * @return model ids
	 */
	int[] modelIds();

	org.powerbot.script.rt4.ClientContext ctx();

	/**
	 * Load the model from the cache
	 * @return model
	 */
	default Model model() {
		final int[] ids = modelIds();

		final CacheModelConfig modelConfig = CacheModelConfig.load(ctx().bot().getCacheWorker(), ids);
		if (modelConfig != null) {
			final Model model = Model.fromCacheModel(ctx(), modelConfig);
			transformModel(model);
			return model;
		}

		return null;
	}

	default void transformModel(final Model model) {}

	/**
	 * Draws the model polygons on the screen for debug purposes
	 * @param g - Graphics object to draw with
	 */
	default void drawModel(final Graphics g) {
		final Model model = model();
		if (model != null) {
			model.draw(localX(), localY(), modelOrientation(), g);
		}
	}

	/**
	 * Get the center point of the model
	 * @return center point
	 */
	default Point modelCenterPoint() {
		final Model model = model();
		if (model == null) {
			return new Point(-1, -1);
		}
		return model.centerPoint(localX(), localY(), modelOrientation());
	}
}
