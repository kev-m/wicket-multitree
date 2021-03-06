/**
 * 
 */
package org.isis.wicket.multitree.applib.content;

import org.apache.isis.viewer.wicket.ui.ComponentFactory;
import org.apache.isis.viewer.wicket.ui.ComponentType;
import org.apache.isis.viewer.wicket.ui.app.registry.ComponentFactoryRegistry;
import org.apache.isis.viewer.wicket.ui.app.registry.ComponentFactoryRegistryAccessor;
import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.extensions.markup.html.repeater.tree.AbstractTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.Folder;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IDetachable;
import org.apache.wicket.model.IModel;
import org.iter.codac.pss.configuration.PlantSystemConfigurationObject;
import org.iter.codac.pss.configuration.Structure;
/**
 * @author kevin
 *
 */
public class StructureContent implements IDetachable {
    private static final long serialVersionUID = 1L;
    private PlantSystemConfigurationObject config;
    
    public StructureContent(PlantSystemConfigurationObject config){
    	this.config = config;
    }

    public Component newContentComponent(String id, final AbstractTree<Structure> tree, IModel<Structure> model)
    {
    	Component component = new Folder<Structure>(id, tree, model){
            private static final long serialVersionUID = 1L;
            
            @Override
            protected Component newLabelComponent(String id, IModel<Structure> model) {
            	return new Label(id, model.toString());
            }
            @Override
            public String toString() {
            	return config.getTitle();
            }
            
        };
        component.setOutputMarkupId(true);
        return component;
    }

	@Override
	public void detach() {
	}
	
    protected ComponentFactory findComponentFactory(final ComponentType componentType, final IModel<?> model) {
        return getComponentRegistry().findComponentFactory(componentType, model);
    }
	
    protected ComponentFactoryRegistry getComponentRegistry() {
        final ComponentFactoryRegistryAccessor cfra = (ComponentFactoryRegistryAccessor) Application.get();
        return cfra.getComponentFactoryRegistry();
    }
    
}