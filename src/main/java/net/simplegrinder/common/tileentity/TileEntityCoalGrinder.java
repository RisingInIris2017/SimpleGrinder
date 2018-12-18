package net.simplegrinder.common.tileentity;

import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.class_1662;
import net.minecraft.class_1732;
import net.minecraft.class_1737;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.text.TextComponent;
import net.minecraft.text.TranslatableTextComponent;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.InventoryUtil;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.simplegrinder.common.blocks.GrinderBlock;
import net.simplegrinder.common.container.GrinderContainer;
import net.simplegrinder.init.InitTileEntity;

import java.util.Iterator;
import java.util.Map;

public class TileEntityCoalGrinder extends LockableContainerBlockEntity implements SidedInventory, class_1732, class_1737, Tickable {
    private static final int[] SLOTS_TOP = new int[]{0};
    private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private static final int[] SLOTS_SIDES = new int[]{1};

    public DefaultedList<ItemStack>inventory;
    public int burnTime;
    public int fuelTime;
    public int cookTime;
    public int cookTimeTotal;
    private TextComponent customName;
    private final Map<Identifier,Integer>recipesUsed;



    public TileEntityCoalGrinder() {
        super(InitTileEntity.TILE_ENTITY_COAL_GRINDER_BLOCK_ENTITY_TYPE);
        this.inventory=DefaultedList.create(3,ItemStack.EMPTY);
        this.recipesUsed= Maps.newHashMap();
    }

    @Override
    public void method_7662(Recipe var1) {
        if(this.recipesUsed.containsKey(var1.getId())){
            this.recipesUsed.put(var1.getId(),this.recipesUsed.get(var1.getId())+1);
        }
        else {
            this.recipesUsed.put(var1.getId(),1);
        }

    }

    @Override
    public Recipe method_7663() {
        return null;
    }

    @Override
    public void method_7683(class_1662 var1) {
        Iterator iterator = this.inventory.iterator();

        while (iterator.hasNext()){
            ItemStack stack= (ItemStack) iterator.next();
            var1.method_7400(stack);
        }
    }

    @Override
    public Container createContainer(PlayerInventory var1, PlayerEntity var2) {
        return new GrinderContainer(var1,this);
    }

    @Override
    public String getContainerId() {
        return "simplegrinder:grinder";
    }

    @Override
    public int[] getInvAvailableSlots(Direction var1) {
        if(var1== Direction.DOWN){
            return SLOTS_BOTTOM;
        }
        else {
            return var1==Direction.UP? SLOTS_TOP:SLOTS_SIDES;
        }
    }

    @Override
    public boolean canInsertInvStack(int var1, ItemStack var2, Direction var3) {
        return this.isValidInvStack(var1, var2);
    }

    @Override
    public boolean canExtractInvStack(int var1, ItemStack var2, Direction var3) {
       if(var3==Direction.DOWN&&var1==1){
           Item i = var2.getItem();
           if(i!= Items.WATER_BUCKET&&i!=Items.BUCKET){
               return false;
           }
       }
       return true;
    }

    @Override
    public int getInvSize() {
        return this.inventory.size();
    }

    @Override
    public boolean isInvEmpty() {
       Iterator iterator = this.inventory.iterator();
       ItemStack itemStack;
       do {
           if(!iterator.hasNext()){
               return true;
           }
           itemStack= (ItemStack) iterator.next();

       }
       while (itemStack.isEmpty());
       return false;
    }

    @Override
    public ItemStack getInvStack(int var1) {
        return this.inventory.get(var1);
    }

    @Override
    public ItemStack takeInvStack(int var1, int var2) {
        return InventoryUtil.splitStack(this.inventory,var1,var2);
    }

    @Override
    public ItemStack removeInvStack(int var1) {
        return InventoryUtil.removeStack(this.inventory,var1);
    }

    @Override
    public void setInvStack(int var1, ItemStack var2) {
        ItemStack itemStack = this.inventory.get(var1);
        boolean flag = !var2.isEmpty()&&var2.isEqualIgnoreTags(itemStack)&&ItemStack.areTagsEqual(var2,itemStack);
        this.inventory.set(var1,var2);
        if(var2.getAmount()>this.getInvMaxStackAmount()){
            itemStack.setAmount(this.getInvMaxStackAmount());
        }
        if(var1==0&&!flag){
            this.cookTimeTotal=this.method_17029();
            this.cookTime=0;
            this.markDirty();
        }
    }

    private int method_17029() {
        return 200;
    }

    @Override
    public boolean canPlayerUseInv(PlayerEntity var1) {
        return var1.squaredDistanceTo(this.pos.getX()+0.5d,this.pos.getY()+0.5d,this.pos.getZ()+0.5d) <=64.0d;
    }

    @Override
    public void clearInv() {
        this.inventory.clear();
    }

    @Override
    public TextComponent getName() {
        return new TranslatableTextComponent("container.grinder", new Object[0]);
    }

    private boolean isBurning() {
        return this.burnTime > 0;
    }
    @Environment(EnvType.CLIENT)
    public static boolean method_11199(Inventory var0) {
        return var0.getInvProperty(0) > 0;
    }
    @Override
    public void tick() {
        boolean var1 = this.isBurning();
        boolean var2 = false;
        if (this.isBurning()) {
            --this.burnTime;
        }

        if (!this.world.isRemote) {
            ItemStack var3 = (ItemStack)this.inventory.get(1);
            if (!this.isBurning() && (var3.isEmpty() || ((ItemStack)this.inventory.get(0)).isEmpty())) {
                if (!this.isBurning() && this.cookTime > 0) {
                    this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
                }
            } else {
                Recipe var4 = this.world.getRecipeManager().get(this, this.world);
                if (!this.isBurning() && this.method_11192(var4)) {
                    this.burnTime = this.method_11200(var3);
                    this.fuelTime = this.burnTime;
                    if (this.isBurning()) {
                        var2 = true;
                        if (!var3.isEmpty()) {
                            Item var5 = var3.getItem();
                            var3.subtractAmount(1);
                            if (var3.isEmpty()) {
                                Item var6 = var5.getContainerItem();
                                this.inventory.set(1, var6 == null ? ItemStack.EMPTY : new ItemStack(var6));
                            }
                        }
                    }
                }

                if (this.isBurning() && this.method_11192(var4)) {
                    ++this.cookTime;
                    if (this.cookTime == this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.method_17029();
                        this.method_11203(var4);
                        var2 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            }

            if (var1 != this.isBurning()) {
                var2 = true;
                this.world.setBlockState(this.pos, (BlockState)this.world.getBlockState(this.pos).with(GrinderBlock.field_11105, this.isBurning()), 3);
            }
        }

        if (var2) {
            this.markDirty();
        }
    }
    protected int method_11200(ItemStack var1) {
        if (var1.isEmpty()) {
            return 0;
        } else {
            Item var2 = var1.getItem();
            return (Integer)method_11196().getOrDefault(var2, 0);
        }
    }

    protected boolean method_11192( Recipe var1) {
        if (!((ItemStack)this.inventory.get(0)).isEmpty() && var1 != null) {
            ItemStack var2 = var1.getOutput();
            if (var2.isEmpty()) {
                return false;
            } else {
                ItemStack var3 = (ItemStack)this.inventory.get(2);
                if (var3.isEmpty()) {
                    return true;
                } else if (!var3.isEqualIgnoreTags(var2)) {
                    return false;
                } else if (var3.getAmount() < this.getInvMaxStackAmount() && var3.getAmount() < var3.getMaxAmount()) {
                    return true;
                } else {
                    return var3.getAmount() < var2.getMaxAmount();
                }
            }
        } else {
            return false;
        }
    }

    private void method_11203(Recipe var1) {
        if (var1 != null && this.method_11192(var1)) {
            ItemStack var2 = (ItemStack)this.inventory.get(0);
            ItemStack var3 = var1.getOutput();
            ItemStack var4 = (ItemStack)this.inventory.get(2);
            if (var4.isEmpty()) {
                this.inventory.set(2, var3.copy());
            } else if (var4.getItem() == var3.getItem()) {
                var4.addAmount(1);
            }

            if (!this.world.isRemote) {
                this.method_7665(this.world, (ServerPlayerEntity)null, var1);
            }

            if (var2.getItem() == Blocks.WET_SPONGE.getItem() && !((ItemStack)this.inventory.get(1)).isEmpty() && ((ItemStack)this.inventory.get(1)).getItem() == Items.BUCKET) {
                this.inventory.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            var2.subtractAmount(1);
        }
    }
    public static Map<Item, Integer> method_11196() {
        Map<Item, Integer> var0 = Maps.newLinkedHashMap();
        method_11202(var0, Items.LAVA_BUCKET, 20000);
        method_11202(var0, Blocks.COAL_BLOCK, 16000);
        method_11202(var0, Items.BLAZE_ROD, 2400);
        method_11202(var0, Items.COAL, 1600);
        method_11202(var0, Items.CHARCOAL, 1600);
        method_11194(var0, ItemTags.LOGS, 300);
        method_11194(var0, ItemTags.PLANKS, 300);
        method_11194(var0, ItemTags.WOODEN_STAIRS, 300);
        method_11194(var0, ItemTags.WOODEN_SLABS, 150);
        method_11194(var0, ItemTags.WOODEN_TRAPDOORS, 300);
        method_11194(var0, ItemTags.WOODEN_PRESSURE_PLATES, 300);
        method_11202(var0, Blocks.OAK_FENCE, 300);
        method_11202(var0, Blocks.BIRCH_FENCE, 300);
        method_11202(var0, Blocks.SPRUCE_FENCE, 300);
        method_11202(var0, Blocks.JUNGLE_FENCE, 300);
        method_11202(var0, Blocks.DARK_OAK_FENCE, 300);
        method_11202(var0, Blocks.ACACIA_FENCE, 300);
        method_11202(var0, Blocks.OAK_FENCE_GATE, 300);
        method_11202(var0, Blocks.BIRCH_FENCE_GATE, 300);
        method_11202(var0, Blocks.SPRUCE_FENCE_GATE, 300);
        method_11202(var0, Blocks.JUNGLE_FENCE_GATE, 300);
        method_11202(var0, Blocks.DARK_OAK_FENCE_GATE, 300);
        method_11202(var0, Blocks.ACACIA_FENCE_GATE, 300);
        method_11202(var0, Blocks.NOTE_BLOCK, 300);
        method_11202(var0, Blocks.BOOKSHELF, 300);
        method_11202(var0, Blocks.JUKEBOX, 300);
        method_11202(var0, Blocks.CHEST, 300);
        method_11202(var0, Blocks.TRAPPED_CHEST, 300);
        method_11202(var0, Blocks.CRAFTING_TABLE, 300);
        method_11202(var0, Blocks.DAYLIGHT_DETECTOR, 300);
        method_11194(var0, ItemTags.BANNERS, 300);
        method_11202(var0, Items.BOW, 300);
        method_11202(var0, Items.FISHING_ROD, 300);
        method_11202(var0, Blocks.LADDER, 300);
        method_11194(var0, ItemTags.SIGNS, 200);
        method_11202(var0, Items.WOODEN_SHOVEL, 200);
        method_11202(var0, Items.WOODEN_SWORD, 200);
        method_11202(var0, Items.WOODEN_HOE, 200);
        method_11202(var0, Items.WOODEN_AXE, 200);
        method_11202(var0, Items.WOODEN_PICKAXE, 200);
        method_11194(var0, ItemTags.WOODEN_DOORS, 200);
        method_11194(var0, ItemTags.BOATS, 200);
        method_11194(var0, ItemTags.WOOL, 100);
        method_11194(var0, ItemTags.WOODEN_BUTTONS, 100);
        method_11202(var0, Items.STICK, 100);
        method_11194(var0, ItemTags.SAPLINGS, 100);
        method_11202(var0, Items.BOWL, 100);
        method_11194(var0, ItemTags.CARPETS, 67);
        method_11202(var0, Blocks.DRIED_KELP_BLOCK, 4001);
        method_11202(var0, Items.CROSSBOW, 300);
        method_11202(var0, Blocks.BAMBOO, 50);
        method_11202(var0, Blocks.DEAD_BUSH, 100);
        method_11202(var0, Blocks.SCAFFOLDING, 50);
        return var0;
    }
    private static void method_11202(Map<Item, Integer> var0, ItemContainer var1, int var2) {
        var0.put(var1.getItem(), var2);
    }
    private static void method_11194(Map<Item, Integer> var0, Tag<Item> var1, int var2) {
        Iterator var3 = var1.values().iterator();

        while(var3.hasNext()) {
            Item var4 = (Item)var3.next();
            var0.put(var4, var2);
        }

    }

    public void setCustomName(TextComponent customName) {
        this.customName = customName;
    }
    public static boolean canUseAsFuel(ItemStack var0) {
        return method_11196().containsKey(var0.getItem());
    }
}
