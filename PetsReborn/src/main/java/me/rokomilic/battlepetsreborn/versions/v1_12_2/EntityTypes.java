package me.rokomilic.battlepetsreborn.versions.v1_12_2;

import me.rokomilic.battlepetsreborn.versions.Util;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EntityTypes {
    BLOCKAS("armor_stand", 30, EntityType.ARMOR_STAND, EntityArmorStand.class, ArmorStandPlus.class),
    CUSTOM("endermite", 67, EntityType.ENDERMITE, EntityEndermite.class, CustomPet.class),
    BAT("bat", 65, EntityType.BAT, EntityBat.class, BatPet.class),
    WITHER("wither", 64, EntityType.WITHER, EntityWither.class, WitherPet.class);

    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends Entity> nmsClass;
    private Class<? extends Entity> customClass;
    private MinecraftKey key;
    private MinecraftKey oldKey;

    EntityTypes(String name, int id, EntityType entityType, Class<? extends Entity> nmsClass, Class<? extends Entity> customClass) {
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
        this.key = new MinecraftKey(name);
        this.oldKey = net.minecraft.server.v1_12_R1.EntityTypes.b.b(nmsClass);
    }

    public static void registerEntities() {
        for (EntityTypes ce : EntityTypes.values())
            ce.register();
    }

    public static void unregisterEntities() {
        for (EntityTypes ce : EntityTypes.values())
            ce.unregister();
    }

    private void register() {
        net.minecraft.server.v1_12_R1.EntityTypes.d.add(key);
        net.minecraft.server.v1_12_R1.EntityTypes.b.a(id, key, customClass);
    }

    private void unregister() {
        net.minecraft.server.v1_12_R1.EntityTypes.d.remove(key);
        net.minecraft.server.v1_12_R1.EntityTypes.b.a(id, oldKey, nmsClass);
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public Class<?> getCustomClass() {
        return customClass;
    }

    @SuppressWarnings("rawtypes")
    public static Map<String, Class> mobs = new HashMap<String, Class>();

    public static void loadMobs() {
        mobs.put("block", ArmorStandPlus.class);
        mobs.put("magma_cube", EntityMagmaCube.class);
        mobs.put("slime", EntitySlime.class);
        mobs.put("cave_spider", EntityCaveSpider.class);
        mobs.put("chicken", EntityChicken.class);
        mobs.put("cow", EntityCow.class);
        mobs.put("creeper", EntityCreeper.class);
        mobs.put("horse", EntityHorse.class);
        mobs.put("iron_golem", EntityIronGolem.class);
        mobs.put("mushroom_cow", EntityMushroomCow.class);
        mobs.put("ocelot", EntityOcelot.class);
        mobs.put("pig", EntityPig.class);
        mobs.put("pig_zombie", EntityPigZombie.class);
        mobs.put("rabbit", EntityRabbit.class);
        mobs.put("sheep", EntitySheep.class);
        mobs.put("silverfish", EntitySilverfish.class);
        mobs.put("skeleton", EntitySkeleton.class);
        mobs.put("snowman", EntitySnowman.class);
        mobs.put("spider", EntitySpider.class);
        mobs.put("villager", EntityVillager.class);
        mobs.put("wolf", EntityWolf.class);
        mobs.put("zombie", EntityZombie.class);
        mobs.put("bat", BatPet.class);
        mobs.put("witch", EntityWitch.class);
        mobs.put("wither", WitherPet.class);
        mobs.put("armorstand", EntityArmorStand.class);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Entity createEntity(String name, World world) {
        Entity entity = null;
        Class localClass = mobs.get(name);
        if (localClass != null) {
            try {
                if (name.equalsIgnoreCase("wither") || name.equalsIgnoreCase("bat")) {
                    entity = (Entity) localClass.getConstructor(new Class[]{World.class, boolean.class}).newInstance(new Object[]{world, true});
                } else
                    entity = (Entity) localClass.getConstructor(new Class[]{World.class}).newInstance(new Object[]{world});
            } catch (Exception e) {
            	e.printStackTrace();
                Bukkit.getLogger().info("[ERROR] Battlepets: No such mobtype: " + name);
            }
        }
        return entity;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Entity spawnEntity(Entity entity, Location loc) {
        World world = entity.world;
        entity.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        //((CraftWorld)loc.getWorld()).getHandle().addEntity(entity, SpawnReason.CUSTOM);

        int x = MathHelper.floor(entity.locX / 16.0D);
        int j = MathHelper.floor(entity.locZ / 16.0D);
        world.getChunkAt(x, j).a(entity);
        world.entityList.add(entity);
        List u = (List) Util.getPrivateField("u", World.class, world);
        for (int i = 0; i < u.size(); i++) {
            ((IWorldAccess) u.get(i)).a(entity);
        }
        IntHashMap entities = (IntHashMap) Util.getPrivateField("entitiesById", World.class, world);
        entities.a(entity.getId(), entity);
        entity.valid = true;
        return entity;
    }

}