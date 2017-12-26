package com.heavenhr.recruit.model.status;

import com.heavenhr.recruit.model.Candidate;

public class InvitedStatusNotifier extends ApplicationStatusNotifer {

	@Override
	public void notify(Candidate candidate) {
		// TODO Can define any kind of output here
		LOGGER.info("Status changed to: invited");
	}

}
