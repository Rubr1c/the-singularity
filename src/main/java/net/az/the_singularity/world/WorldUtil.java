package net.az.the_singularity.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Function;

public class WorldUtil {

    @FunctionalInterface
    public interface BlockBreakHandler {
        void handle(ItemStack pStack,
                    Level pLevel,
                    BlockPos pPos,
                    ServerLevel serverLevel,
                    BlockState pState,
                    LivingEntity pEntityLiving);
    }

    public static void createTunnel(ItemStack pStack,
                                    Level pLevel,
                                    BlockPos pPos,
                                    BlockState pState,
                                    Player player,
                                    Direction verticalDirection,
                                    int tunnelLength, BlockBreakHandler handler) {
        for (int i = 1; i <= tunnelLength; i++) {
            BlockPos targetPos = pPos.relative(verticalDirection, i);
            BlockState targetState = pLevel.getBlockState(targetPos);
            if (targetState.getDestroySpeed(pLevel, targetPos) >= 0 &&
                    targetState.getBlock().canHarvestBlock(targetState, pLevel, targetPos, player)) {
                if (handler == null) {
                    pLevel.destroyBlock(targetPos, true, player);
                } else if (pLevel instanceof ServerLevel serverLevel) {
                    handler.handle(pStack, pLevel, targetPos, serverLevel, pState, player);
                }
            }
        }
    }

    public static void breakWithMode(CompoundTag tag,
                                     ItemStack pStack,
                                     Level pLevel,
                                     BlockState pState,
                                     BlockPos pPos,
                                     LivingEntity pEntityLiving,
                                     BlockBreakHandler handler) {
        if (tag.getInt("mode") == 1) {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    BlockPos targetPos = pPos.offset(dx, 0, dz);
                    BlockState targetState = pLevel.getBlockState(targetPos);

                    if (targetState.getDestroySpeed(pLevel, targetPos) >= 0 &&
                            pState.getBlock().canHarvestBlock(targetState, pLevel, targetPos, (Player) pEntityLiving)) {
                        if (handler == null) {
                            pLevel.destroyBlock(targetPos, true, pEntityLiving);
                        } else if (pLevel instanceof ServerLevel serverLevel) {
                            handler.handle(pStack, pLevel, targetPos, serverLevel, pState, pEntityLiving);
                        }
                    }
                }

            }
        } else if (tag.getInt("mode") == 2) {
            Player player = (Player) pEntityLiving;
            float yaw = player.getYRot() % 360;
            if (yaw < 0) {
                yaw += 360;
            }

            float pitch = player.getXRot();

            Direction horizontalDirection;
            if (yaw >= 45 && yaw < 135) {
                horizontalDirection = Direction.WEST;
            } else if (yaw >= 135 && yaw < 225) {
                horizontalDirection = Direction.NORTH;
            } else if (yaw >= 225 && yaw < 315) {
                horizontalDirection = Direction.EAST;
            } else {
                horizontalDirection = Direction.SOUTH;
            }

            Direction verticalDirection = null;
            boolean lookingVertical = false;

            if (pitch <= -30) {
                verticalDirection = Direction.UP;
                lookingVertical = true;
            } else if (pitch >= 30) {
                verticalDirection = Direction.DOWN;
                lookingVertical = true;
            }

            int tunnelLength = 25;

            if (lookingVertical) {
                createTunnel(pStack, pLevel, pPos, pState, player, verticalDirection, tunnelLength, handler);
            } else {
                createTunnel(pStack, pLevel, pPos, pState, player, horizontalDirection, tunnelLength, handler);
            }
        }
    }
}
