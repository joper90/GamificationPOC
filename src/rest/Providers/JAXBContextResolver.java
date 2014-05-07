package rest.Providers;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;

import rest.model.PlayerDetails;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;



@Provider
public class JAXBContextResolver implements ContextResolver < JAXBContext > {

    private JAXBContext contextSoft;
    @SuppressWarnings("rawtypes")
	private Class[] types = {PlayerDetails.class};

    public JAXBContextResolver() throws Exception {
    	
    	JSONConfiguration build = JSONConfiguration.mapped()
    			.arrays("softAchivements")
    			.arrays("hardAchivements")
    			.build();
    	
        this.contextSoft = new JSONJAXBContext(build,types);
    }

    @SuppressWarnings("rawtypes")
	public JAXBContext getContext(Class objectType) {
        for (Class type : types) {
            if (type == objectType) {
                return contextSoft;
            }
        }
        return null;
    }
}
//This makes sure the arrays are overidding and returned as an array, NOT a eelement.