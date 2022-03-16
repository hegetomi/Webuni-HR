package hu.webuni.hr.hegetomi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties("hr")
@Component
public class HrConfigProperties {

    private Raise raise = new Raise();

    public Raise getRaise() {
        return raise;
    }

    public static class Raise {
        private Default def = new Default();
        private Smart smart = new Smart();


        public Default getDef() {
            return def;
        }

        public void setDef(Default def) {
            this.def = def;
        }

        public Smart getSmart() {
            return smart;
        }

        public void setSpecial(Smart smart) {
            this.smart = smart;
        }


        public static class Default {
            private int percent;

            public int getPercent() {
                return percent;
            }

            public void setPercent(int percent) {
                this.percent = percent;
            }
        }

        public static class Smart {
            private List<Integer> percent;
            private List<Double> since;

            public List<Integer> getPercent() {
                return percent;
            }

            public void setPercent(List<Integer> percent) {
                this.percent = percent;
            }

            public List<Double> getSince() {
                return since;
            }

            public void setSince(List<Double> since) {
                this.since = since;
            }
        }

    }
}

