package com.sber.reboot.client.model;

/**
 *  <h2>Состояние банкомата</h2>
 *  <ul>
 *      <li>OFFLINE - Не подключен к процессингу</li>
 *      <li>STANDBY - Ожидание карты клиента</li>
 *      <li>SERVICE - Обслуживание клиента по карте</li>
 *  </ul>
 */
public enum State {
    OFFLINE,
    STANDBY,
    SERVICE
}
