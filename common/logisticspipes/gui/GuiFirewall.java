package logisticspipes.gui;

import logisticspipes.network.GuiIDs;
import logisticspipes.pipes.PipeItemsFirewall;
import logisticspipes.utils.gui.BasicGuiHelper;
import logisticspipes.utils.gui.DummyContainer;
import logisticspipes.utils.gui.GuiStringHandlerButton;
import logisticspipes.utils.gui.GuiStringHandlerButton.StringHandler;
import logisticspipes.utils.gui.KraphtBaseGuiScreen;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;

public class GuiFirewall extends KraphtBaseGuiScreen {
	
	private PipeItemsFirewall pipe;
	
	public GuiFirewall(PipeItemsFirewall pipe, EntityPlayer player) {
		super(230, 230, 0, 0);
		this.pipe = pipe;
		DummyContainer dummy = new DummyContainer(player.inventory, pipe.inv);
		dummy.addNormalSlotsForPlayerInventory(33, 147);
		for(int x = 0;x < 6;x++) {
			for(int y = 0;y < 6;y++) {
				dummy.addDummySlot(x*6 + y, x*18 + 9, y*18 + 31);
			}
		}
		this.inventorySlots = dummy;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		controlList.clear();
		controlList.add(new GuiStringHandlerButton(0, width / 2 + 23, height / 2 + 29 - 123, 60 , 20, new StringHandler() {@Override public String getContent() {return pipe.isBlocking() ? "Blocked" : "Allowed";}}));
		controlList.add(new GuiStringHandlerButton(1, width / 2 + 23, height / 2 + 62 - 123, 60 , 20, new StringHandler() {@Override public String getContent() {return pipe.isBlockProvider() ? "Blocked" : "Allowed";}}));
		controlList.add(new GuiStringHandlerButton(2, width / 2 + 23, height / 2 + 95 - 123, 60 , 20, new StringHandler() {@Override public String getContent() {return pipe.isBlockCrafer() ? "Blocked" : "Allowed";}}));
		controlList.add(new GuiStringHandlerButton(3, width / 2 + 23, height / 2 + 128- 123, 60 , 20, new StringHandler() {@Override public String getContent() {return pipe.isBlockSorting() ? "Blocked" : "Allowed";}}));
	}

	@Override
	public int getGuiID() {
		return GuiIDs.GUI_FIREWALL;
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		switch (button.id) {
		case 0:
			pipe.setBlocking(!pipe.isBlocking());
			break;
		case 1:
			pipe.setBlockProvider(!pipe.isBlockProvider());
			break;
		case 2:
			pipe.setBlockCrafer(!pipe.isBlockCrafer());
			break;
		case 3:
			pipe.setBlockSorting(!pipe.isBlockSorting());
			break;
		default:
			break;
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		BasicGuiHelper.drawGuiBackGround(mc, guiLeft, guiTop, right, bottom, zLevel, true);
		BasicGuiHelper.drawPlayerInventoryBackground(mc, guiLeft + 33, guiTop + 147);
		mc.fontRenderer.drawString("Firewall", guiLeft + 45, guiTop + 8, 0x404040);
		mc.fontRenderer.drawString("Filter:", guiLeft + 10, guiTop + 18, 0x404040);
		for(int x = 0;x < 6;x++) {
			for(int y = 0;y < 6;y++) {
				BasicGuiHelper.drawSlotBackground(mc, guiLeft + x*18 + 8, guiTop + y*18 + 30);
			}
		}
		mc.fontRenderer.drawString("Filtered items are:", guiLeft + 125, guiTop + 10, 0x404040);
		mc.fontRenderer.drawString("Block Provider:", guiLeft + 133, guiTop + 43, 0x404040);
		mc.fontRenderer.drawString("Block Crafting:", guiLeft + 134, guiTop + 76, 0x404040);
		mc.fontRenderer.drawString("Block Sorting:", guiLeft + 137, guiTop + 109, 0x404040);
	}
}
