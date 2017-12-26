package com.heavenhr.recruit.model.status;

import com.heavenhr.recruit.model.Candidate;

public class AppliedStatusNotifier extends ApplicationStatusNotifer {

	@Override
	public void notify(Candidate candidate) {
		// TODO Can define any kind of output here
		LOGGER.info("Status changed to: applied");
	}

}
