package atm.model.atm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by igor on 14.08.16.
 */

public class Card {
    private UUID GUID;
    private final String CARD_UUID_PATH = "src/atm/cardUUID";

    public UUID getGUID() {
        return GUID;
    }

    public Card() throws CardException, IOException {
        readCart();
    }

    private boolean verifyCard(String id) {
        if (id != null && id.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            System.err.println("Карта идентифицирована.");
            return true;
        } else {
//            IOController.showMsg("Ошибка карты", "Карта не идентифицирована.");
            return false;
        }
    }

    private void readCart() throws IOException, CardException {
        try(BufferedReader in = new BufferedReader(new FileReader(CARD_UUID_PATH))) {
            String id = in.readLine();
            if (verifyCard(id)) {
                GUID = UUID.fromString(id);
            } else {
                throw new CardException("Не могу прочесть идентификатор карты");
            }
        }
    }

}
