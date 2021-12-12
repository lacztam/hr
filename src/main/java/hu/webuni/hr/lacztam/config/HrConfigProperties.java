package hu.webuni.hr.lacztam.config;

import java.util.TreeMap;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {

	private Salary salary = new Salary();

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	
	public static class Salary {
		private Def def = new Def();
		private Smart smart = new Smart();

		public Def getDef() {
			return def;
		}

		public void setDef(Def def) {
			this.def = def;
		}

		public Smart getSmart() {
			return smart;
		}

		public void setSmart(Smart smart) {
			this.smart = smart;
		}
	}

	public static class Def {
		private int percent;

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
	}

	public static class Smart {
		private double percent1;
		private double percent2;
		private double percent3;
		private double limit1;
		private double limit2;
		private double limit3;
		TreeMap<Double, Integer> limits;

		public TreeMap<Double, Integer> getLimits() {
			return limits;
		}

		public void setLimits(TreeMap<Double, Integer> limits) {
			this.limits = limits;
		}

		public double getPercent1() {
			return percent1;
		}

		public void setPercent1(double percent1) {
			this.percent1 = percent1;
		}

		public double getPercent2() {
			return percent2;
		}

		public void setPercent2(double percent2) {
			this.percent2 = percent2;
		}

		public double getPercent3() {
			return percent3;
		}

		public void setPercent3(double percent3) {
			this.percent3 = percent3;
		}

		public double getLimit1() {
			return limit1;
		}

		public void setLimit1(double limit1) {
			this.limit1 = limit1;
		}

		public double getLimit2() {
			return limit2;
		}

		public void setLimit2(double limit2) {
			this.limit2 = limit2;
		}

		public double getLimit3() {
			return limit3;
		}

		public void setLimit3(double limit3) {
			this.limit3 = limit3;
		}
	}
}
