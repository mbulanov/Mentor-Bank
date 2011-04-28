package ru.mentorbank.backoffice.services.moneytransfer;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.mentorbank.backoffice.dao.OperationDao;
import ru.mentorbank.backoffice.dao.exception.OperationDaoException;
import ru.mentorbank.backoffice.dao.stub.OperationDaoStub;
import ru.mentorbank.backoffice.model.Operation;
import ru.mentorbank.backoffice.model.stoplist.JuridicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.PhysicalStopListRequest;
import ru.mentorbank.backoffice.model.stoplist.StopListInfo;
import ru.mentorbank.backoffice.model.stoplist.StopListStatus;
import ru.mentorbank.backoffice.model.transfer.AccountInfo;
import ru.mentorbank.backoffice.model.transfer.JuridicalAccountInfo;
import ru.mentorbank.backoffice.model.transfer.PhysicalAccountInfo;
import ru.mentorbank.backoffice.model.transfer.TransferRequest;
import ru.mentorbank.backoffice.services.accounts.AccountService;
import ru.mentorbank.backoffice.services.accounts.AccountServiceBean;
import ru.mentorbank.backoffice.services.moneytransfer.exceptions.TransferException;
import ru.mentorbank.backoffice.services.stoplist.StopListService;
import ru.mentorbank.backoffice.services.stoplist.StopListServiceStub;
import ru.mentorbank.backoffice.test.AbstractSpringTest;

import static org.mockito.Mockito.*;

public class MoneyTransferServiceTest extends AbstractSpringTest {

	@Autowired
	private MoneyTransferServiceBean moneyTransferService;

	@Before
	public void setUp() {
	}

	@Test
	public void transfer() throws TransferException, OperationDaoException {
		//fail("not implemented yet");
		// TODO: (Completed) Ќеобходимо протестировать, что дл€ хорошего перевода всЄ 
		// работает и вызываютс€ все необходимые методы сервисов
		// ƒалее следует закоментированна€ заготовка
		
		StopListService mockedStopListService = mock(StopListServiceStub.class);
		AccountService mockedAccountService = mock(AccountServiceBean.class);
		OperationDao mockedOperationDao = mock(OperationDaoStub.class);
		
		JuridicalAccountInfo srcAcc = new JuridicalAccountInfo();
		PhysicalAccountInfo dstAcc = new PhysicalAccountInfo();
		
		srcAcc.setInn(StopListServiceStub.INN_FOR_OK_STATUS);
		dstAcc.setAccountNumber("1234567890");
		
		
		//чтобы не править textContext прописываем руками
		MoneyTransferServiceBean moneyTransferService = new MoneyTransferServiceBean();
		moneyTransferService.setAccountService(mockedAccountService);
		moneyTransferService.setStopListService(mockedStopListService);
		moneyTransferService.setOperationDao(mockedOperationDao);
		
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setSrcAccount(srcAcc);
		transferRequest.setDstAccount(dstAcc);	
		
		//похорошему тут можно было бы .verifyBalance (srcAcc)
		when(mockedAccountService.verifyBalance( any(AccountInfo.class) )).thenReturn(true);
		
		StopListInfo slInfo = new StopListInfo();
		slInfo.setStatus(StopListStatus.OK);
		//чтобы не вызвать stub, а сразу работать
		when(mockedStopListService.getJuridicalStopListInfo(any(JuridicalStopListRequest.class)))
			.thenReturn(slInfo);
		when(mockedStopListService.getPhysicalStopListInfo(any(PhysicalStopListRequest.class)))
		.thenReturn(slInfo);
		
		moneyTransferService.transfer(transferRequest);
		
		
		
		verify(mockedStopListService).getJuridicalStopListInfo(any(JuridicalStopListRequest.class));
		verify(mockedAccountService).verifyBalance(srcAcc);
		
		verify(mockedOperationDao).saveOperation(any(Operation.class));
	}
}
