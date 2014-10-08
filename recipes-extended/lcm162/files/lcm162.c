/*
 *    __                        __      _
 *   / /__________ __   _____  / /___  (_)___  ____ _
 *  / __/ ___/ __ `/ | / / _ \/ / __ \/ / __ \/ __ `/
 * / /_/ /  / /_/ /| |/ /  __/ / /_/ / / / / / /_/ /
 * \__/_/   \__,_/ |___/\___/_/ .___/_/_/ /_/\__, /
 *                           /_/            /____/
 *
 * (c) Travelping GmbH <info@travelping.com>
 *
 */

#include <stdio.h>
#include <unistd.h>
#include <getopt.h>
#include <sys/io.h>
#include <time.h>
#include <string.h>
#include <stdlib.h>
#include <sys/time.h>

#define 	LPT_DATA_OUT		0x378		//Which Paralle Port use 
#define 	LPT_CTL_OUT		0x37a		//PORT+2
/* All refference the LCM Data Sheet */
#define 	LCD_CLEAR		0x001
#define 	LCD_HOME		0x002
#define 	LCD_ENT			0x004
#define 	LCD_ENT_INC		0x002
#define 	LCD_ENT_SHIFT		0x001
#define 	LCD_DISP		0x008
#define 	LCD_DISP_ON		0x004
#define 	LCD_DISP_CURS_ON	0x002
#define 	LCD_DISP_BLINK_ON	0x001
#define 	LCD_SET_FUNC		0x020
#define 	LCD_FUNC_8BITS		0x010
#define 	LCD_FUNC_2LINE		0x008
#define 	LCD_SET_CG_ADDR		0x040
#define 	LCD_SET_DD_ADDR		0x080
/* These simple code ,I use Macro way to make it work */
#define		lcd_set_blinking_cursor(onoff)	if (onoff) 	\
				lcd_cmd(LCD_DISP|LCD_DISP_ON|LCD_DISP_CURS_ON|LCD_DISP_BLINK_ON);\
				else lcd_cmd(LCD_DISP|LCD_DISP_ON|LCD_DISP_CURS_ON);							
						
const char helpmsg[] =
	"Usage: %s [OPTION] {up to 16 chars} [up to 16 more chars]\n"
	"Spawns a message on the external display.\n\n"
	"Options:\n"
	"  -2          display message on the second row\n"
	"  -b off      switch backlight on or off, default on\n\n";

static unsigned char bl = 0;

void lcd_cmd(int para) {
	outb(0x08 | bl, LPT_CTL_OUT);
	outb(para, LPT_DATA_OUT);
	outb(0x0A | bl, LPT_CTL_OUT);
	usleep(800);
}

void lcd_goto_rc(int row, int col){
	col &= 0x1f;
	col |= LCD_SET_DD_ADDR;
	if (row)
	 	col |=0x40;
	lcd_cmd(col);
}

void lcd_write(int wdata){
	outb(0x00 | bl, LPT_CTL_OUT);
	outb(wdata, LPT_DATA_OUT);
	outb(0x02 | bl, LPT_CTL_OUT);
	usleep(40);
}

void lcd_init(void){
	lcd_cmd(LCD_SET_FUNC | LCD_FUNC_8BITS | LCD_FUNC_2LINE);
	lcd_cmd(LCD_DISP | LCD_DISP_ON);
//	lcd_cmd(LCD_ENT | LCD_ENT_INC);
//	lcd_cmd(8|LCD_SET_CG_ADDR);
	lcd_cmd(LCD_HOME);
	lcd_cmd(LCD_CLEAR);
}

/* get backlight status and change it when nessesary */
void lcd_set_backlight()
{
	unsigned char in = inb(LPT_CTL_OUT) & ~0x01;
	outb(in | bl, LPT_CTL_OUT);
}

void writeln(char *msg, int ln){
	int c = 1, ml;
	if (ln == 1)
		lcd_cmd(LCD_HOME);
	else if (ln == 2)
		lcd_goto_rc(1, 0);
	else {
		printf("Line %i ain't there, eh!?",ln);
		return;
	}
	for (c = 0; c < 20 && msg[c]; c++)
		lcd_write((int)msg[c]);
}

int main(int argc, char **argv){
	int row = 1;
	int opt;

	while ((opt = getopt(argc, argv, "2b:")) != -1) {
		switch (opt) {
		case '2':
			row = 2;
			break;

		case 'b':
			if (strcasecmp("on", optarg) == 0)
				bl = 0;
			else if (strcasecmp("off", optarg) == 0)
				bl = 1;
			else {
				fprintf(stderr, helpmsg, argv[0]);
				exit(EXIT_FAILURE);
			}
			break;

		default: /* '?' */
			printf("help\n");
			fprintf(stderr, helpmsg, argv[0]);
			exit(EXIT_FAILURE);
		}
	}

	if (ioperm(LPT_DATA_OUT, 3, 3) < 0) {
		perror("ioperm");
		exit(EXIT_FAILURE);
	}

	lcd_set_backlight();

	if (optind < argc) {
		lcd_init();
		while (row <= 2 && argv[optind]) {
			writeln(argv[optind], row);
			optind++;
			row++;
		}
	}
	return 0;
}
