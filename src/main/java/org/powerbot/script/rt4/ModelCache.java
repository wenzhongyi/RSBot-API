package org.powerbot.script.rt4;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ModelCache {

	private static final int CACHE_SIZE = 256;
	private static Map<Integer, Model> models = new ConcurrentHashMap<>();

	private static void removeOneIfFull() {
		if (models.size() == CACHE_SIZE) {
			models.remove(models.keySet().iterator().next());
		}
	}

	public static Model load(final ClientContext ctx, final int id) {
		removeOneIfFull();

		return models.computeIfAbsent(id, modelId -> {
			final CacheModelConfig cacheModel =  CacheModelConfig.load(ctx.bot().getCacheWorker(), id);
			if (cacheModel == null) {
				return null;
			}

			return Model.fromCacheModel(ctx, cacheModel);
		});
	}

	public static Model load(final ClientContext ctx, final int[] ids) {
		removeOneIfFull();
		return models.computeIfAbsent(Arrays.hashCode(ids), unused -> {
			final CacheModelConfig cacheModel =  CacheModelConfig.load(ctx.bot().getCacheWorker(), ids);
			if (cacheModel == null) {
				return null;
			}

			return Model.fromCacheModel(ctx, cacheModel);
		});
	}
}
