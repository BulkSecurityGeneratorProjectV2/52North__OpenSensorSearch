
package org.n52.oss.stats;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.n52.oss.api.StatisticsResource;
import org.n52.sir.ds.IGetCapabilitiesDAO;
import org.n52.sir.ows.OwsExceptionReport;
import org.skyscreamer.jsonassert.JSONAssert;

public class Statistics {

    private static IGetCapabilitiesDAO dao;

    private static Long sensors = Long.valueOf(42l);
    private static Long phenomena = Long.valueOf(1l);
    private static Long services = Long.valueOf(17l);

    private static String sensorResponse = "{ \"sensors\": " + sensors.toString() + " }";
    private static String phenomenaResponse = "{ \"phenomena\": " + phenomena.toString() + "}";
    private static String servicesResponse = "{ \"services\": " + services.toString() + "}";

    private StatisticsResource resource;

    @BeforeClass
    public static void prepare() throws OwsExceptionReport {
        dao = mock(IGetCapabilitiesDAO.class);

        when(dao.getSensorCount()).thenReturn(sensors);
        when(dao.getPhenomenonCount()).thenReturn(phenomena);
        when(dao.getServiceCount()).thenReturn(services);
    }

    @Before
    public void setUp() {
        this.resource = new StatisticsResource(dao, "TEST_URL");
    }

    @Test
    public void sensorCount() throws JSONException {
        Response response = this.resource.getNumberOfSensors();
        String actual = (String) response.getEntity();

        JSONAssert.assertEquals(sensorResponse, actual, false);
    }

    @Test
    public void phenomenonCount() throws JSONException {
        Response response = this.resource.getNumberOfPhenomena();
        String actual = (String) response.getEntity();

        JSONAssert.assertEquals(phenomenaResponse, actual, false);
    }

    @Test
    public void servicesCount() throws JSONException {
        Response response = this.resource.getNumberOfServices();
        String actual = (String) response.getEntity();

        JSONAssert.assertEquals(servicesResponse, actual, false);
    }
}
