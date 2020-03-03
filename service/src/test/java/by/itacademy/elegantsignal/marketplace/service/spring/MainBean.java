package by.itacademy.elegantsignal.marketplace.service.spring;

public class MainBean {

	private InnerBean1 bean1;
	private InnerBean2 bean2;

	public InnerBean1 getBean1() {
		return bean1;
	}

	public void setBean1(InnerBean1 bean1) {
		this.bean1 = bean1;
	}

	public InnerBean2 getBean2() {
		return bean2;
	}

	public void setBean2(InnerBean2 bean2) {
		this.bean2 = bean2;
	}

	@Override
	public String toString() {
		return "MainBean [bean1=" + bean1 + ", bean2=" + bean2 + "]";
	}
}