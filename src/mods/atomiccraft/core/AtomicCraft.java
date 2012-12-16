package mods.atomiccraft.core;

import java.util.List;

import mods.atomiccraft.blocks.BaseItem;
import mods.atomiccraft.blocks.BlockAtomicSplitter;
import mods.atomiccraft.blocks.BlockLantern;
import mods.atomiccraft.blocks.ItemCheese;
import mods.atomiccraft.core.GameReg;
import mods.atomiccraft.core.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@NetworkMod(clientSideRequired = true, serverSideRequired = false)
@Mod(modid = "AtomicCraft", name = "AtomicCraft", version = "0.1")
public class AtomicCraft {
	
	public final static Item coinGold = new BaseItem(9001).setItemName("coinGold").setCreativeTab(CreativeTabs.tabMisc);
	public final static Item coinIron = new BaseItem(9002).setItemName("coinIron").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(1);
	public final static Item coinCopper = new BaseItem(9003).setItemName("coinCopper").setCreativeTab(CreativeTabs.tabMisc).setIconIndex(2);
	
	public final static Item nuggetIron = new BaseItem(9004).setItemName("nuggetIron").setCreativeTab(CreativeTabs.tabMaterials).setIconIndex(3);
	public final static Item nuggetCopper = new BaseItem(9005).setItemName("nuggetCopper").setCreativeTab(CreativeTabs.tabMaterials).setIconIndex(4);
	
	public final static Item foodCheese = new ItemCheese(9006, 4, 2.0F ,false).setItemName("foodCheese").setIconIndex(5);
	
	public final static Block blockLantern = new BlockLantern(2000, 0, Material.glass).setCreativeTab(CreativeTabs.tabDecorations).setHardness(2.0F).setStepSound(Block.soundGlassFootstep).setLightValue(1.0F).setBlockName("blockGlowLantern");
	
	public final static Block blockAtomicSplitter = new BlockAtomicSplitter(2001, 1, Material.iron).setCreativeTab(CreativeTabs.tabRedstone).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockAtomicSplitter");
	
	@SidedProxy(clientSide = "mods.atomiccraft.core.proxy.ClientProxy", serverSide = "mods.atomiccraft.core.proxy.CommonProxy", bukkitSide ="mods.mikecraft.core.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInitMod(FMLPreInitializationEvent event){
			MinecraftForge.EVENT_BUS.register(new EventContainer());
	}
	
	@Init
	public void initMod(FMLInitializationEvent event) {
		
		GameReg.registerBlock(blockLantern);
		GameReg.registerBlock(blockAtomicSplitter);
		proxy.registerClientTextures();
		GameReg.addSmelting(coinGold.shiftedIndex, new ItemStack(Item.goldNugget, 1));
		GameReg.addSmelting(coinIron.shiftedIndex, new ItemStack(nuggetIron, 1));
		GameReg.addSmelting(coinCopper.shiftedIndex, new ItemStack(nuggetCopper, 1));
		GameReg.addSmelting(ItemBucketMilk.bucketMilk.shiftedIndex, new ItemStack(foodCheese, 1));
		GameReg.addRecipe(new ItemStack(blockLantern, 2), new Object[] {
			"XX", "XX", 'X', Block.glowStone
		});
		GameReg.addRecipe(new ItemStack(Item.ingotIron, 1), new Object[]{
				"XXX", "XXX", "XXX", 'X', AtomicCraft.nuggetIron
		});
		
	}

	
}