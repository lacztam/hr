package hu.webuni.hr.lacztam.config;

import java.util.Date;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.auth0.jwt.algorithms.Algorithm;

@ConfigurationProperties(prefix = "hr")
@Component
public class HrConfigProperties {

	private JwtTokenProperties jwtTokenProperties = new JwtTokenProperties();
	
	public JwtTokenProperties getJwtTokenProperties() {
		return jwtTokenProperties;
	}

	public void setJwtTokenProperties(JwtTokenProperties jwtTokenProperties) {
		this.jwtTokenProperties = jwtTokenProperties;
	}
	
	public static class JwtTokenProperties{
		private String auth;
		private String issuer;
		private Date expireTime;
		protected int expireMinute;
		private Algorithm algorithm;
		private String algorithmSpecification;
		private String algorithmSpecificationArgument;

		public Date getExpireTime() {
			this.expireTime = new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(this.expireMinute));
			return expireTime;
		}
		public Algorithm getAlgorithm() {
			if(getAlgorithmSpecification().equals("HMAC256"))
				this.algorithm = Algorithm.HMAC256(this.algorithmSpecificationArgument);
			if(getAlgorithmSpecification().equals("HMAC384"))
				this.algorithm = Algorithm.HMAC384(this.algorithmSpecificationArgument);
			
			return algorithm;
		}
		public String getAlgorithmSpecification() {
			return algorithmSpecification;
		}
		public String getAlgorithmSpecificationArgument() {
			return algorithmSpecificationArgument;
		}
		public String getIssuer() {
			return issuer;
		}
		public void setIssuer(String issuer) {
			this.issuer = issuer;
		}
		public void setExpireTime(Date expireTime) {
			this.expireTime = expireTime;
		}
		public void setAlgorithm(Algorithm algorithm) {
			this.algorithm = algorithm;
		}
		public void setAlgorithmSpecification(String algorithmSpecification) {
			this.algorithmSpecification = algorithmSpecification;
		}
		public void setAlgorithmSpecificationArgument(String algorithmSpecificationArgument) {
			this.algorithmSpecificationArgument = algorithmSpecificationArgument;
		}
		public int getExpireMinute() {
			return expireMinute;
		}
		public void setExpireMinute(int expireMinute) {
			this.expireMinute = expireMinute;
		}
		public String getAuth() {
			return auth;
		}
		public void setAuth(String auth) {
			this.auth = auth;
		}
	}
	
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
