import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(CapacitorAudioOutputListPlugin)
public class CapacitorAudioOutputListPlugin: CAPPlugin {
    private let implementation = CapacitorAudioOutputList()

    @objc func echo(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.echo(value)
        ])
    }

    @objc func getAudioDevices(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.getAudioDevices(value)
        ])
    }

    @objc func changeOutputDevice(_ call: CAPPluginCall) {
        let value = call.getString("value") ?? ""
        call.resolve([
            "value": implementation.changeOutputDevice(value)
        ])
    }
}
