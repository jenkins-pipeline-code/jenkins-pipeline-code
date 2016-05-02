package webapp;

import org.apfloat.Apfloat;
import org.apfloat.samples.Pi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PiController {


    @RequestMapping("/pi")
    @ResponseBody
    public Map<String, String> pi() {
        return pi(10);
    }

    @RequestMapping("/pi/{digits}")
    @ResponseBody
    public Map<String, String> pi(@PathVariable int digits) {
        Pi.setErr(new PrintWriter(System.out));

        Map<String, String> result = new HashMap<>();

        long start = System.currentTimeMillis();
        Apfloat pi = new Pi.ChudnovskyPiCalculator(digits, 10).execute();
        long duration = System.currentTimeMillis() - start;

        result.put("duration", duration + "");
        result.put("result", pi.toString());

        return result;
    }

}