package lotto.game;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoBundle;
import lotto.domain.Score;
import lotto.domain.Result;
import lotto.global.Store;
import lotto.ui.Input;
import lotto.ui.Output;

public class Game {
    public void run() {
        int count = Store.convertPriceToCount(Input.getPurchasePrice());
        Output.printPurchaseCount(count);
        LottoBundle lottoBundle = Store.generateLottoBundle(count);
        Output.printLottoBundle(lottoBundle);
        Result result = makeLottoResult();
        Score score = lottoBundle.checkResult(result);
        Output.printLottoResult(score);
    }

    private Result makeLottoResult() {
        try {
            final Lotto winningLotto = getUserLotto();
            final int bonusBall = Input.getBonusNumber();
            return new Result(winningLotto, bonusBall);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return makeLottoResult();
        }
    }

    private Lotto getUserLotto() {
        try {
            final List<Integer> userLottoNumber = Input.getUserNumbers();
            return new Lotto(userLottoNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return getUserLotto();
        }
    }
}
