<?php

use Illuminate\Database\Seeder;

class AdminCreate extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $admin = new Role();
        $admin->name = 'admin';
        $admin->display_name = 'Administrator';
        $admin->save();

        $user = User::where('email','=','cmrlweb@gmail.com')->first();
        $user->attachRole($admin);
    }
}
