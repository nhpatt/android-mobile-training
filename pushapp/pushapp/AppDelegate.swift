//
//  AppDelegate.swift
//  pushapp
//
//  Created by Javier Gamarra Olmedo on 12/20/17.
//  Copyright © 2017 Javier Gamarra Olmedo. All rights reserved.
//

import UIKit
import UserNotifications
import LRMobileSDK
import LRPush

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate, UNUserNotificationCenterDelegate {

	var window: UIWindow?

	func application(_ application: UIApplication,
					 didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {

		let deviceTokenString = deviceToken.map {
			String(format: "%02.2hhx", $0) }.joined()

		print("APNs device token: \(deviceTokenString)")

		let session = LRSession.init(
			server: "http://192.168.50.223:8080",
			authentication:
			LRBasicAuthentication(username: "test@liferay.com", password: "test"))


		LRPush.withSession(session!)
			.onSuccess({ (value) in print(value)})
			.onFailure({ (error) in print(error)})
			.withPortalVersion(70)
			.registerDeviceToken(deviceTokenString)

		let center = UNUserNotificationCenter.current()
		center.delegate = self

		center.requestAuthorization(options: [.sound,.alert,.badge]) { (granted, error) in
			if granted {
				print("Notification Enable Successfully")
			} else{
				print("Some Error Occure")
			}
		}
	}

	func userNotificationCenter(_ center: UNUserNotificationCenter, willPresent notification: UNNotification, withCompletionHandler completionHandler: @escaping (_ options:UNNotificationPresentationOptions) -> Void) {
		print("Handle push from foreground")
		print("\(notification.request.content.userInfo)")
	}

	func userNotificationCenter(_ center: UNUserNotificationCenter, didReceive response:UNNotificationResponse, withCompletionHandler completionHandler: @escaping () -> Void) {
		print("Handle push from background or closed")
		print("\(response.notification.request.content.userInfo)")
	}

	func application(_ application: UIApplication,
					 didFailToRegisterForRemoteNotificationsWithError error: Error) {
		print("Remote notification support is unavailable due to error: \(error.localizedDescription)")
	}


	func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
		// Override point for customization after application launch.
		return true
	}

	func applicationWillResignActive(_ application: UIApplication) {
		// Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
		// Use this method to pause ongoing tasks, disable timers, and invalidate graphics rendering callbacks. Games should use this method to pause the game.
	}

	func applicationDidEnterBackground(_ application: UIApplication) {
		// Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
		// If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
	}

	func applicationWillEnterForeground(_ application: UIApplication) {
		// Called as part of the transition from the background to the active state; here you can undo many of the changes made on entering the background.
	}

	func applicationDidBecomeActive(_ application: UIApplication) {
		// Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
	}

	func applicationWillTerminate(_ application: UIApplication) {
		// Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.
	}


}

