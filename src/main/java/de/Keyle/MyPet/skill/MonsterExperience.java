/*
 * This file is part of MyPet
 *
 * Copyright (C) 2011-2013 Keyle
 * MyPet is licensed under the GNU Lesser General Public License.
 *
 * MyPet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * MyPet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.Keyle.MyPet.skill;

import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class MonsterExperience {
    public static final Map<EntityType, MonsterExperience> mobExp = new HashMap<EntityType, MonsterExperience>();
    private static MonsterExperience unknown = new MonsterExperience(0., EntityType.UNKNOWN);

    static {
        mobExp.put(EntityType.SKELETON, new MonsterExperience(5., EntityType.SKELETON));
        mobExp.put(EntityType.ZOMBIE, new MonsterExperience(5., EntityType.ZOMBIE));
        mobExp.put(EntityType.SPIDER, new MonsterExperience(5., EntityType.SPIDER));
        mobExp.put(EntityType.WOLF, new MonsterExperience(1., 3., EntityType.WOLF));
        mobExp.put(EntityType.CREEPER, new MonsterExperience(5., EntityType.CREEPER));
        mobExp.put(EntityType.GHAST, new MonsterExperience(5., EntityType.GHAST));
        mobExp.put(EntityType.PIG_ZOMBIE, new MonsterExperience(5., EntityType.PIG_ZOMBIE));
        mobExp.put(EntityType.ENDERMAN, new MonsterExperience(5., EntityType.ENDERMAN));
        mobExp.put(EntityType.CAVE_SPIDER, new MonsterExperience(5., EntityType.CAVE_SPIDER));
        mobExp.put(EntityType.MAGMA_CUBE, new MonsterExperience(1., 4., EntityType.MAGMA_CUBE));
        mobExp.put(EntityType.SLIME, new MonsterExperience(1., 4., EntityType.SLIME));
        mobExp.put(EntityType.SILVERFISH, new MonsterExperience(5., EntityType.SILVERFISH));
        mobExp.put(EntityType.BLAZE, new MonsterExperience(10., EntityType.BLAZE));
        mobExp.put(EntityType.GIANT, new MonsterExperience(25., EntityType.GIANT));
        mobExp.put(EntityType.COW, new MonsterExperience(1., 3., EntityType.COW));
        mobExp.put(EntityType.PIG, new MonsterExperience(1., 3., EntityType.PIG));
        mobExp.put(EntityType.CHICKEN, new MonsterExperience(1., 3., EntityType.CHICKEN));
        mobExp.put(EntityType.SQUID, new MonsterExperience(1., 3., EntityType.SQUID));
        mobExp.put(EntityType.SHEEP, new MonsterExperience(1., 3., EntityType.SHEEP));
        mobExp.put(EntityType.OCELOT, new MonsterExperience(1., 3., EntityType.OCELOT));
        mobExp.put(EntityType.MUSHROOM_COW, new MonsterExperience(1., 3., EntityType.MUSHROOM_COW));
        mobExp.put(EntityType.VILLAGER, new MonsterExperience(0., EntityType.VILLAGER));
        mobExp.put(EntityType.SNOWMAN, new MonsterExperience(0., EntityType.SNOWMAN));
        mobExp.put(EntityType.IRON_GOLEM, new MonsterExperience(0., EntityType.IRON_GOLEM));
        mobExp.put(EntityType.ENDER_DRAGON, new MonsterExperience(20000., EntityType.ENDER_DRAGON));
        mobExp.put(EntityType.WITCH, new MonsterExperience(10., EntityType.WITCH));
        mobExp.put(EntityType.BAT, new MonsterExperience(1., EntityType.BAT));
        mobExp.put(EntityType.ENDER_CRYSTAL, new MonsterExperience(10., EntityType.ENDER_CRYSTAL));
        mobExp.put(EntityType.WITHER, new MonsterExperience(100., EntityType.WITHER));
    }

    private double min;
    private double max;
    private EntityType entityType;

    public MonsterExperience(double min, double max, EntityType entityType) {
        if (max >= min) {
            this.max = max;
            this.min = min;
        } else if (max <= min) {
            this.max = min;
            this.min = max;
        }
        this.entityType = entityType;
    }

    public MonsterExperience(double exp, EntityType entityType) {
        this.max = exp;
        this.min = exp;
        this.entityType = entityType;
    }

    public double getRandomExp() {
        return max == min ? max : ((int) (doubleRandom(min, max) * 100)) / 100.;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setMin(double min) {
        this.min = min;
        if (min > max) {
            max = min;
        }
    }

    public void setMax(double max) {
        this.max = max;
        if (max < min) {
            min = max;
        }
    }

    public void setExp(double exp) {
        max = (min = exp);
    }

    private static double doubleRandom(double low, double high) {
        return Math.random() * (high - low) + low;
    }

    @Override
    public String toString() {
        return entityType.getName() + "{min=" + min + ", max=" + max + "}";
    }

    public static boolean hasMonsterExperience(EntityType type) {
        return mobExp.containsKey(type);
    }

    public static MonsterExperience getMonsterExperience(EntityType type) {
        if (mobExp.containsKey(type)) {
            return mobExp.get(type);
        }
        return unknown;
    }
}