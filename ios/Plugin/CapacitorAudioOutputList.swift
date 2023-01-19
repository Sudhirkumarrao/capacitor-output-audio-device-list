import Foundation

@objc public class CapacitorAudioOutputList: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }

    @objc public func getAudioDevices(_ value: String) -> String {
        print(value)
        return value
    }

    @objc public func changeOutputDevice(_ value: String) -> String {
        print(value)
        return value
    }
}
