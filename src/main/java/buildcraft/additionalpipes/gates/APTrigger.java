package buildcraft.additionalpipes.gates;

import buildcraft.additionalpipes.AdditionalPipes;
import buildcraft.api.core.render.ISprite;
import buildcraft.api.statements.IStatement;
import buildcraft.api.statements.StatementManager;
import buildcraft.lib.client.sprite.SpriteHolderRegistry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class APTrigger implements IStatement {

	protected String id; //used as unique key in BC registry
	protected String descriptionKey; //used for description translations
    protected ResourceLocation texture;

    @SideOnly(Side.CLIENT)
    private final ISprite sprite;

    /**
     * 
     * @param id the name of the trigger, without the "additionalpipes:" prefix.  Also the name of the texture.
     */
	public APTrigger(String idWithoutPrefix)
	{
		descriptionKey = "trigger." + idWithoutPrefix;
		
		this.id = "additionalpipes:" + descriptionKey;
		StatementManager.statements.put(this.id, this);		
		sprite = SpriteHolderRegistry.getHolder(new ResourceLocation(AdditionalPipes.MODID, "items/triggers/" + idWithoutPrefix));
		
        MinecraftForge.EVENT_BUS.register(this);		

	}
    
    @Override
    public String getUniqueTag()
    {
    	return id;
    }
    
	@Override
	public String getDescription()
	{
		return I18n.translateToLocal(descriptionKey);
	}
	
	@Override
	public ISprite getSprite()
	{
		return sprite;
	}
	

}
