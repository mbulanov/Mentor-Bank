package ru.mentorbank.backoffice.services.stoplist;

import org.junit.runner.notification.StoppedByUserException;

import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.PhysicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;
import ru.mentorbank.backoffice.services.stoplist.StopListService;

public class StopListServiceStub implements StopListService {

	public static final String INN_FOR_OK_STATUS = "1111111111111";
	public static final String INN_FOR_STOP_STATUS = "22222222222222";
	public static final String INN_FOR_ASKSECURITY_STATUS = "33333333333333";

	@Override
	public StopListInfo getJuridicalStopListInfo(
			JuridicalStopListRequest request) {
		StopListInfo stopListInfo = new StopListInfo();
		stopListInfo.setComment("stopListInfo юр. лица");
		if (INN_FOR_OK_STATUS.equals(request.getInn())){			
			stopListInfo.setStatus(StopListStatus.OK);
		} else if (INN_FOR_STOP_STATUS.equals(request.getInn())) {
			stopListInfo.setStatus(StopListStatus.STOP);			
		} else {
			stopListInfo.setStatus(StopListStatus.ASKSECURITY);			
		}
		return stopListInfo;
	}

	@Override
	public StopListInfo getPhysicalStopListInfo(PhysicalStopListRequest request) {
		//TODO: (Completed) Реализовать
		StopListInfo stopListInfo = new StopListInfo();
		stopListInfo.setComment("stopListInfo физ. лица");
		
		if ("1234567890".equals(request.getDocumentSeries())){
			stopListInfo.setStatus(StopListStatus.OK);
		//имя просто для разнообразия, т.к. всё равно нет test'a для него
		} else if ("Василий".equals(request.getFirstname())){
			stopListInfo.setStatus(StopListStatus.STOP);
		} else {
			stopListInfo.setStatus(StopListStatus.ASKSECURITY);
		}
		
		return stopListInfo;
	}

}
