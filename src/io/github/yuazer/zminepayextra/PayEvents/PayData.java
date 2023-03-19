package io.github.yuazer.zminepayextra.PayEvents;

import io.github.yuazer.zminepayextra.Utils.DataUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.minepay.api.events.MinePaySuccessEvent;
import top.minepay.bean.TradeInfo;

public class PayData implements Listener {
    @EventHandler
    public void onPaySuccess(MinePaySuccessEvent e) {
        Player player = Bukkit.getPlayer(e.getTradeInfo().getPlayerName());
        TradeInfo info = e.getTradeInfo();
        DataUtils.addPlayerDay(player, info.getPrice());
        DataUtils.addPlayerWeek(player, info.getPrice());
        DataUtils.addPlayerMonth(player, info.getPrice());
    }
}
