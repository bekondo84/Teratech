/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import java.awt.Frame;

/**
 *
 * @author BAKARI
 */
public class Messages {

    public static void Messages(Frame parent, boolean modal, NotificationType notificationType, String message, String details, String option) {
        if (notificationType.equals(NotificationType.ERROR)) {

            Notification.getNotificationDialog(parent, modal, notificationType, message, details);

        } else if (notificationType.equals(NotificationType.SUCCES)) {
            Succes.getSuccessDialog(parent, modal, notificationType, message);

        } else if (notificationType.equals(NotificationType.INFOS)) {
            Infos.getInfosDialog(parent, modal, notificationType, message);
        } else {

        }
    }

    public static boolean Messages(Frame parent, boolean modal, NotificationType notificationType, String message, String element) {
        if (notificationType.equals(NotificationType.WARNING)) {
            return Confirmation.getConfirmationDialog(parent, modal, notificationType, message, element);
        } else {
            return false;
        }
    }
}
