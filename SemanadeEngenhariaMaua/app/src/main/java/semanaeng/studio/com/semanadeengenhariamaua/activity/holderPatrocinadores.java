package semanaeng.studio.com.semanadeengenhariamaua.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import semanaeng.studio.com.semanadeengenhariamaua.R;

/**
 * Created by hecto on 15/06/2017.
 */

public class holderPatrocinadores {
    ImageView Image;
    TextView Nome;
    public holderPatrocinadores(View v) {
        Image = (ImageView) v.findViewById(R.id.image_patrocinadores);
        Nome = (TextView) v.findViewById(R.id.text_nome_patrocinadores);
    }
}
