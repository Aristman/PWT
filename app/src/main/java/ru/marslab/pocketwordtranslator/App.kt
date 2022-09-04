package ru.marslab.pocketwordtranslator

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import org.kodein.di.DI
import org.kodein.di.DIAware
import ru.marslab.pocketwordtranslator.data.dataModule
import ru.marslab.pocketwordtranslator.domain.di.domainModule
import ru.marslab.pocketwordtranslator.presentation.di.viewModelModule
import ru.marslab.pocketwordtranslator.presentation.navigation.mainModule

class App : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(dataModule)
        import(domainModule)
        import(viewModelModule)
    }

    override fun onCreate() {
        super.onCreate()
        ScreenRegistry {
            mainModule()
        }
    }
}
