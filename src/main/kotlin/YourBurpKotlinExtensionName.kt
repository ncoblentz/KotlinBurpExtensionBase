import burp.api.montoya.BurpExtension
import burp.api.montoya.MontoyaApi
import burp.api.montoya.ui.settings.SettingsPanelBuilder
import burp.api.montoya.ui.settings.SettingsPanelPersistence
import com.nickcoblentz.montoya.CollabHelper
import com.nickcoblentz.montoya.CollabHelperPanelSettings
import com.nickcoblentz.montoya.settings.PanelSettingsDelegate


// Montoya API Documentation: https://portswigger.github.io/burp-extensions-montoya-api/javadoc/burp/api/montoya/MontoyaApi.html
// Montoya Extension Examples: https://github.com/PortSwigger/burp-extensions-montoya-api-examples

class YourBurpKotlinExtensionName : BurpExtension {
    private lateinit var api: MontoyaApi
    private val projectSettings : MyProjectSettings by lazy { MyProjectSettings() }

    companion object {
        const val EXTENSION_NAME = "Your Burp Kotlin Extension Name"
    }




    override fun initialize(api: MontoyaApi?) {

        // In Kotlin, you have to explicitly define variables as nullable with a ? as in MontoyaApi? above
        // This is necessary because the Java Library allows null to be passed into this function
        // requireNotNull is a built-in Kotlin function to check for null that throws an Illegal Argument exception if it is null
        // after checking for null, the Kotlin compiler knows that any reference to api  or this.api below will not = null and you no longer have to check it
        // Finally, assign the MontoyaApi instance (not nullable) to a class property to be accessible from other functions in this class
        this.api = requireNotNull(api) { "api : MontoyaApi is not allowed to be null" }
        // This will print to Burp Suite's Extension output and can be used to debug whether the extension loaded properly
        api.logging().logToOutput("Started loading the extension...")


        // Name our extension when it is displayed inside of Burp Suite
        api.extension().setName(EXTENSION_NAME)

        // Code for setting up your extension starts here...



        // Just a simple hello world to start with
        api.logging().logToOutput("Hello Extension Writer!")

        // Code for setting up your extension ends here
        api.userInterface().registerSettingsPanel(projectSettings.settingsPanel)

        // See logging comment above
        api.logging().logToOutput("...Finished loading the extension")

    }
}


class MyProjectSettings() {
    val settingsPanelBuilder : SettingsPanelBuilder = SettingsPanelBuilder.settingsPanel()
        .withPersistence(SettingsPanelPersistence.PROJECT_SETTINGS) // you can change this to user settings if you wish
        .withTitle(YourBurpKotlinExtensionName.EXTENSION_NAME)
        .withDescription("Add your description here")
        .withKeywords("Add Keywords","Here")

    private val settingsManager = PanelSettingsDelegate(settingsPanelBuilder)

    val example1Setting: String by settingsManager.stringSetting("An example string setting here", "test default value here")
    val example2Setting: Boolean by settingsManager.booleanSetting("An example boolean setting here", false)

    val settingsPanel = settingsManager.buildSettingsPanel()
}