package net.az.the_singularity.items.tools;

import net.az.the_singularity.items.ModTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
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
                    LootParams.Builder lootBuilder = new LootParams.Builder(serverLevel)
                            .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pPos))
                            .withParameter(LootContextParams.TOOL, pStack)
                            .withOptionalParameter(LootContextParams.THIS_ENTITY, pEntityLiving)
                            .withOptionalParameter(LootContextParams.BLOCK_ENTITY, pLevel.getBlockEntity(pPos));

                    List<ItemStack> drops = pState.getDrops(lootBuilder);

                    for (ItemStack drop : drops) {
                        Block.popResource(pLevel, pPos, drop);
                    }

                    int fortuneLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, pStack);
                    int silkTouchLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, pStack);

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
