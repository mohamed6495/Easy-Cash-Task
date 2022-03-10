package com.mina_mikhail.base_mvvm.domain.general.use_case

import com.mina_mikhail.base_mvvm.domain.general.repository.GeneralRepository
import javax.inject.Inject

class CheckFirstTimeUseCase @Inject constructor(private val generalRepository: GeneralRepository) {

  operator fun invoke() = generalRepository.isFirstTime()
}