@extends('app')

@section('content')

{!! Form::open(array('url' => '/maintainence/add', 'class' => 'form-horizontal')) !!}
	
	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('mnlabel','Maintainence id: ')!!}
		</div>
    	<div class="col-sm-2">
      		{!!Form::text('mnid',$lastmain->mnid+1)!!}
    	</div>
    	<div class="col-sm-4">
    		{!!Form::label('mnwarning','Warning! Dont Change Value.')!!}
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('preequip','Pre Existing Equipments:')!!}
		</div>
    	<div class="col-sm-10">
      		<select id="selector" name="selector" class="form-control" multiple="multiple">
      			@foreach($Equip as $index => $equipment)
      			<option value="{{$index + 1}}">{{$equipment->Name}}</option>
      			@endforeach
      		</select>
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('namelabel','Name: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('mnname')!!}
      		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('timerlabel','Time Maintainence: ')!!}
		</div>
    	<div class="col-sm-2">
      		{!!Form::text('timer')!!}
      		
    	</div>
    	<div class="col-sm-4">
			{!!Form::label('mnwarning2','Warning! Specify in no of Days.')!!}
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<input type="hidden" name="_token" value="{{ csrf_token() }}">
			<input type="submit" value="Submit">
		</div>
	</div>


{!!Form::close()!!}

@endsection