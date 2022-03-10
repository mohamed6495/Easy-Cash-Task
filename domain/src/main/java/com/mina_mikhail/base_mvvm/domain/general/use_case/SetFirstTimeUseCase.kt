package com.mina_mikhail.base_mvvm.domain.general.use_case

import com.mina_mikhail.base_mvvm.domain.general.repository.GeneralRepository
import javax.inject.Inject

class SetFirstTimeUseCase @Inject constructor(private val generalRepository: GeneralRepository) {

  operator fun invoke(isFirstTime: Boolean) = generalRepository.setFirstTime(isFirstTime)
}