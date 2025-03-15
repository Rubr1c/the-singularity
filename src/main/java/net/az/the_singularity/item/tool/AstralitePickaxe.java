package net.az.the_singularity.item.tool;

import net.az.the_singularity.init.ModTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

import java.util.List;

public class AstralitePickaxe extends PickaxeItem {

    public AstralitePickaxe() {
        super(ModTiers.ASTRALITE, 1, 1, new Item.Properties());
    }

    @Override
    public boolean mineBlock(ItemStack pStack,
                             Level pLevel,
                             BlockState pState,
                             BlockPos pPos,
                             LivingEntity pEntityLiving) {
        if (!pLevel.isClientSide()) {
            if (pState.is(Tags.Blocks.ORES)) {
                if (pLevel instanceof ServerLevel serverLevel) {
                    int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, pStack);
                    int silkTouchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, pStack);

                    LootParams.Builder lootBuilder = new LootParams.Builder(serverLevel)
                            .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pPos))
                            .withParameter(LootContextParams.TOOL, pStack)
                            .withOptionalParameter(LootContextParams.THIS_ENTITY, pEntityLiving)
                            .withOptionalParameter(LootContextParams.BLOCK_ENTITY, pLevel.getBlockEntity(pPos));

                    List<ItemStack> drops = pState.getDrops(lootBuilder);

                    if (fortuneLevel > 0 && silkTouchLevel == 0) {
                        for (int i = 0; i < drops.size(); i++) {
                            ItemStack drop = drops.get(i);
                            if (!drop.isEmpty()) {
                                int bonusAmount = Math.max(0, pLevel.getRandom().nextInt(fortuneLevel + 2) - 1);
                                drop.grow(bonusAmount);
                            }
                        }
                    }

                    for (ItemStack drop : drops) {
                        Block.popResource(pLevel, pPos, drop);
                    }

                    int xpAmount = pState.getExpDrop(serverLevel, pLevel.getRandom(), pPos, fortuneLevel, silkTouchLevel);
                    if (xpAmount > 0) {
                        pState.getBlock().popExperience(serverLevel, pPos, xpAmount * 2);
                    }
                }
            }
        }
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }
}