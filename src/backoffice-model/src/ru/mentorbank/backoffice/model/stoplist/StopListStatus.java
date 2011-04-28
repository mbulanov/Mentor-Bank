package ru.mentorbank.backoffice.model.stoplist;

public enum StopListStatus {
	OK("�K"), ASKSECURITY("��������� ���������� � ������ ������������"), STOP(
			"����������� ��������");
	private final String value;

	private StopListStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;

	}
}
