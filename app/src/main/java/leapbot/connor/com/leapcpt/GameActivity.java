package leapbot.connor.com.leapcpt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Button controlButton = (Button) findViewById(R.id.Controls);
        controlButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(GameActivity.this, RobotActivity.class));
            }
        });

        final Button settingButton = (Button) findViewById(R.id.Settings);
        settingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(GameActivity.this, SettingActivity.class));
            }
        });


        final Button game1Button = (Button) findViewById(R.id.game1);
        game1Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("3 * 2 + 1 = ?");


                final EditText input = new EditText(GameActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);


                builder.setPositiveButton("Answer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        if (text.equalsIgnoreCase("7")) {
                            final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
                            progress.setProgress(progress.getProgress() + 15);
                            final TextView progressText = (TextView) findViewById(R.id.progresstext);
                            progressText.setText("Progess Comeplete: " + progress.getProgress() + "%");
                            game1Button.setEnabled(false);
                            Toast.makeText(getApplicationContext(), "Correct!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        final Button game2Button = (Button) findViewById(R.id.game2);
        game2Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("3 + 5 * 5 = ?");


                final EditText input = new EditText(GameActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);


                builder.setPositiveButton("Answer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        if (text.equalsIgnoreCase("28")) {
                            final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
                            progress.setProgress(progress.getProgress() + 15);
                            final TextView progressText = (TextView) findViewById(R.id.progresstext);
                            progressText.setText("Progess Comeplete: " + progress.getProgress() + "%");
                            game2Button.setEnabled(false);
                            Toast.makeText(getApplicationContext(), "Correct!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        final Button game3Button = (Button) findViewById(R.id.game3);
        game3Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("3 ^ 3");


                final EditText input = new EditText(GameActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);


                builder.setPositiveButton("Answer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        if (text.equalsIgnoreCase("27")) {
                            final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
                            progress.setProgress(progress.getProgress() + 15);
                            final TextView progressText = (TextView) findViewById(R.id.progresstext);
                            progressText.setText("Progess Comeplete: " + progress.getProgress() + "%");
                            game3Button.setEnabled(false);
                            Toast.makeText(getApplicationContext(), "Correct!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        final Button game4Button = (Button) findViewById(R.id.game4);
        game4Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("(1/2) * 50");


                final EditText input = new EditText(GameActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);


                builder.setPositiveButton("Answer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        if (text.equalsIgnoreCase("25")) {
                            final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
                            progress.setProgress(progress.getProgress() + 15);
                            final TextView progressText = (TextView) findViewById(R.id.progresstext);
                            progressText.setText("Progess Comeplete: " + progress.getProgress() + "%");
                            game4Button.setEnabled(false);
                            Toast.makeText(getApplicationContext(), "Correct!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        final Button game5Button = (Button) findViewById(R.id.game5);
        game5Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("25 ^ 0.5");


                final EditText input = new EditText(GameActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);


                builder.setPositiveButton("Answer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        if (text.equalsIgnoreCase("5")) {
                            final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
                            progress.setProgress(progress.getProgress() + 15);
                            final TextView progressText = (TextView) findViewById(R.id.progresstext);
                            progressText.setText("Progess Comeplete: " + progress.getProgress() + "%");
                            game5Button.setEnabled(false);
                            Toast.makeText(getApplicationContext(), "Correct!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        final Button game6Button = (Button) findViewById(R.id.game6);
        game6Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("200 * 10 / 250 + 50");


                final EditText input = new EditText(GameActivity.this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);


                builder.setPositiveButton("Answer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String text = input.getText().toString();
                        if (text.equalsIgnoreCase("58")) {
                            final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
                            progress.setProgress(progress.getProgress() + 25);
                            final TextView progressText = (TextView) findViewById(R.id.progresstext);
                            progressText.setText("Progess Comeplete: " + progress.getProgress() + "%");
                            game6Button.setEnabled(false);
                            Toast.makeText(getApplicationContext(), "Correct!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

    }
}
