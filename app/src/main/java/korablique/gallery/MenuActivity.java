package korablique.gallery;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private static final String OPEN_MARKET_URI = "market://apps/dev?id=";
    private static final String OPEN_BROWSER_URI = "https://play.google.com/store/apps/dev?id=";
    private static final String DEV_ID = "9141303443900639327"; // for example, yandex

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        findViewById(R.id.gallery).setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.our_apps).setOnClickListener(v -> {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(OPEN_MARKET_URI + DEV_ID)));
                return;
            } catch (ActivityNotFoundException anfe) {
                // if there is no Play Market app, try to open the page in browser
            }
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(OPEN_BROWSER_URI + DEV_ID)));
            } catch (ActivityNotFoundException anfe) {
                Toast.makeText(this, R.string.could_not_display_page, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
